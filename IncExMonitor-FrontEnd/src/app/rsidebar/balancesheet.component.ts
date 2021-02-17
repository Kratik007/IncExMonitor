import { Component, OnInit, ViewChild , ViewEncapsulation, QueryList, ViewChildren} from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatabaseService } from '../service/database.service';
import { AuthenticationService } from '../service/authentication.service';
// export interface incomeelement{
//   income:string;
//   amount:number;
//   }

//   export interface expenseselement{
//     expenses:string;
//     amount:number;
//     }

// const ELEMENT_DATA_INCOMES:incomeelement[]=[
//   {income:'Consultancy Revenue',amount:3048},
//   {income:'Consultancy Revenue',amount:6432},
//   {income:'Consultancy Revenue',amount:1232},
//   {income:'Consultancy Revenue',amount:3048},
//   {income:'Consultancy Revenue',amount:6432},
//   {income:'Consultancy Revenue',amount:1234},
//   {income:'Consultancy Revenue',amount:4234},
// ]

// const ELEMENT_DATA_EXPENSES:expenseselement[]=[
//   {expenses:'Electric Bill',amount:3748},
//   {expenses:'Salary Expenses',amount:3428},
//   {expenses:'OfficeExpenses',amount:2348},
//   {expenses:'Electric Bill',amount:5343},
//   {expenses:'Salary Expenses',amount:3428},
//   {expenses:'OfficeExpenses',amount:24548}
// ]

@Component({
  selector: 'app-balancesheet',
  templateUrl: './balancesheet.component.html',
  styleUrls: ['./balancesheet.component.css'],
  encapsulation:ViewEncapsulation.None
})

export class BalancesheetComponent implements OnInit {
  @ViewChild('table1',{read:MatSort,static:true}) sort1: MatSort;
  @ViewChild('table2',{read:MatSort,static:true}) sort2:MatSort;
  @ViewChild('incomePag',{read:MatPaginator,static:true}) paginator1:MatPaginator;
  @ViewChild('expensesPag',{read:MatPaginator,static:true}) paginator2:MatPaginator;
  showSpinner: boolean = false;
  
  minDate;
  maxDate;
  tableSpinner:boolean=false;
  expensesDataSource:MatTableDataSource<null>;
  incomesDataSource:MatTableDataSource<null>;                       
  expensesheetDet=new Array
  incomesheetDet=new Array
  width:number;
  totalExpenses=0;
  totalIncomes=0;
  constructor(private db:DatabaseService,private auth:AuthenticationService) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
  }
  
     expenseColumn:string[]=['expenses','amount'];
     incomeColumn:string[]=['income','amount'];
    //  dataSource1=new MatTableDataSource(ELEMENT_DATA_EXPENSES);
    //  dataSource2=new MatTableDataSource(ELEMENT_DATA_INCOMES);

  ngOnInit(): void {
    this.width=screen.width;
    this.getData();
  }

  balanceSheet = new FormGroup({
    dateFrom: new FormControl("",Validators.required),
    dateTo: new FormControl("",Validators.required),
  })

  
  getData(datefrom=null, dateto=null) {
    if(datefrom>dateto){
      alert("\"Date From\" cannot be greater than \"Date To\"")
    }else if(datefrom==null && dateto==null){
      this.totalIncomes=0;
      this.totalExpenses=0;
    this.tableSpinner = true;
    this.expensesheetDet=new Array;
    this.incomesheetDet=new Array;
      this.db.getBalanceSheetByUsername(this.auth.getAuthenticatedUserId()).then((data:string)=>{
        let balanceDetails = JSON.parse(data);
        balanceDetails.forEach(element => {
         let obj={
           "amount":element.amount,
           "acountName":element.acountName
         }
         if(element.pay_receive=="RECEIVE"){
           this.totalIncomes+=element.amount;
           this.incomesheetDet.push(obj);
         }
         else{
           this.totalExpenses+=element.amount;
           this.expensesheetDet.push(obj);
         }
        });
        this.expensesDataSource=new MatTableDataSource(this.expensesheetDet);
        this.expensesDataSource.sort=this.sort1
        this.expensesDataSource.paginator=this.paginator2
        this.tableSpinner=false;
        this.incomesDataSource=new MatTableDataSource(this.incomesheetDet);
        this.incomesDataSource.sort=this.sort2
        this.incomesDataSource.paginator=this.paginator1
      }).catch(err=>{
        this.tableSpinner=false;
        console.log(err)
      })
  }else{
    alert(datefrom+","+dateto);
      this.totalIncomes=0;
      this.totalExpenses=0;
      this.tableSpinner = true;
      this.expensesheetDet=new Array;
      this.incomesheetDet=new Array;
      this.db.getBalanceSheetBetweenDates(this.auth.getAuthenticatedUserId(),datefrom,dateto).then((data:string)=>{
        let balanceDetails = JSON.parse(data);
        console.log(data);
        balanceDetails.forEach(element => {
         let obj={
           "amount":element.amount,
           "acountName":element.acountName
         }
         if(element.pay_receive=="RECEIVE"){
           this.totalIncomes+=element.amount;
           this.incomesheetDet.push(obj);
         }
         else{
           this.totalExpenses+=element.amount;
           this.expensesheetDet.push(obj);
         }
        });
        this.expensesDataSource=new MatTableDataSource(this.expensesheetDet);
        this.expensesDataSource.sort=this.sort1
        this.expensesDataSource.paginator=this.paginator2
        this.tableSpinner=false;
        this.incomesDataSource=new MatTableDataSource(this.incomesheetDet);
        this.incomesDataSource.sort=this.sort2
        this.incomesDataSource.paginator=this.paginator1
      }).catch(err=>{
        this.tableSpinner=false;
        console.log(err)
      })
  }
}
  findRecord(){
    if(!this.balanceSheet.invalid){
    this.expensesheetDet=new Array;
    this.incomesheetDet=new Array;
    let datefrom = `${this.balanceSheet.get('dateFrom').value.getFullYear()}-${this.balanceSheet.get('dateFrom').value.getMonth()+1}-${this.balanceSheet.get('dateFrom').value.getDate()}`;
    let dateto = `${this.balanceSheet.get('dateTo').value.getFullYear()}-${this.balanceSheet.get('dateTo').value.getMonth()+1}-${this.balanceSheet.get('dateTo').value.getDate()}`;
    this.getData(datefrom,dateto);
    }else{

    }
}
resetForm(){
  this.balanceSheet.reset();
  this.balanceSheet.get('dateFrom').setErrors(null);
  this.balanceSheet.get('dateTo').setErrors(null);
  }

  getErrorMsg(field){
    if(this.balanceSheet.get('dateFrom').hasError('required')){
      return 'Enter Required Date';
    }
    else
      return '';
  }

}
