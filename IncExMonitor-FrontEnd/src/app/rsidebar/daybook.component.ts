import { Component, OnInit, ViewChild , ViewEncapsulation} from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { AuthenticationService } from '../service/authentication.service';
import { DatabaseService } from '../service/database.service';

@Component({
  selector: 'app-daybook',
  templateUrl: './daybook.component.html',
  styleUrls: ['./daybook.component.css'],
  encapsulation:ViewEncapsulation.None
})
export class DaybookComponent implements OnInit {
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
  expensesbookDet=new Array
  incomesbookDet=new Array
  width:number;
  // dataSources;
  constructor(private auth:AuthenticationService,private db:DatabaseService) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
  }
  
     columnDef:string[]=['accountname','date','amount','pay_recieveby','remark'];
    //  dataSource1=new MatTableDataSource(ELEMENT_DATA_EXPENSES);
    //  dataSource2=new MatTableDataSource(ELEMENT_DATA_INCOMES);
  
  ngOnInit(): void {
    this.width=screen.width;
  }

  ngAfterViewInit():void{
    this.getData();
  }

  dayBook = new FormGroup({
    dateFrom: new FormControl("",Validators.required),
    dateTo: new FormControl("",Validators.required),
  })

  getData(datefrom=null, dateto=null) {
    if(datefrom>dateto){
      alert("\"Date From\" cannot be greater than \"Date To\"")
    }
    else if(datefrom==null && dateto==null){
    this.tableSpinner = true;
    this.expensesbookDet=new Array;
    this.incomesbookDet=new Array;
    this.db.getAllDayBookDetails(this.auth.getAuthenticatedUserId()).then((data:string)=>{
      let elements=JSON.parse(data);
      console.log(data);
      elements.forEach(element => {
        if(element.pay_receive=="RECEIVE"){
          let obj={
            "acountName":element.acountName,
            "date": element.date,
            "amount": element.amount,
            "receiveBy": "",
            "remark": element.remark
          }
          if(element.by=="c"){
            obj.receiveBy="CASH"
          }else{
            obj.receiveBy="BANK"
          }
          this.incomesbookDet.push(obj);
        }else{
          let obj={
            "acountName":element.acountName,
            "date": element.date,
            "amount": element.amount,
            "payedBy": "",
            "remark": element.remark
          }
          if(element.payedBy=="c"){
            obj.payedBy="CASH"
          }else{
            obj.payedBy="BANK"
          }
          this.expensesbookDet.push(obj);
        }
      });
      this.tableSpinner=false;
      this.expensesDataSource=new MatTableDataSource(this.expensesbookDet);
      this.expensesDataSource.sort=this.sort1
      this.expensesDataSource.paginator=this.paginator2
      this.incomesDataSource=new MatTableDataSource(this.incomesbookDet);
      this.incomesDataSource.sort=this.sort2
      this.incomesDataSource.paginator=this.paginator1    
    }).catch(err=>{
      console.log(err);
      this.tableSpinner=false;
    });
  }
  else{
    this.tableSpinner = true;
    this.db.getDayBookDetailsBetweenDates(this.auth.getAuthenticatedUserId(),datefrom,dateto).then((data:string)=>{
      let elements=JSON.parse(data);
      elements.forEach(element => {
        if(element.pay_receive=="RECEIVE"){
          let obj={
            "acountName":element.acountName,
            "date": element.date,
            "amount": element.amount,
            "receiveBy": "",
            "remark": element.remark
          }
          if(element.by=="c"){
            obj.receiveBy="CASH"
          }else{
            obj.receiveBy="BANK"
          }
          this.incomesbookDet.push(obj);
        }else{
          let obj={
            "acountName":element.acountName,
            "date": element.date,
            "amount": element.amount,
            "payedBy": "",
            "remark": element.remark
          }
          if(element.by=="c"){
            obj.payedBy="CASH"
          }else{
            obj.payedBy="BANK"
          }
          this.expensesbookDet.push(obj);
        }
      });
      this.tableSpinner=false;
      this.expensesDataSource=new MatTableDataSource(this.expensesbookDet);
      this.expensesDataSource.sort=this.sort1
      this.expensesDataSource.paginator=this.paginator2
      this.incomesDataSource=new MatTableDataSource(this.incomesbookDet);
      this.incomesDataSource.sort=this.sort2
      this.incomesDataSource.paginator=this.paginator1    
    }).catch(err=>{
      console.log(err);
      this.tableSpinner=false;
    });
  }
}

  findRecord(){
    if(!this.dayBook.invalid){
    this.expensesbookDet=new Array;
    this.incomesbookDet=new Array;
    let datefrom = `${this.dayBook.get('dateFrom').value.getFullYear()}-${this.dayBook.get('dateFrom').value.getMonth()+1}-${this.dayBook.get('dateFrom').value.getDate()}`;
    let dateto = `${this.dayBook.get('dateTo').value.getFullYear()}-${this.dayBook.get('dateTo').value.getMonth()+1}-${this.dayBook.get('dateTo').value.getDate()}`;
    this.getData(datefrom,dateto);
    }
    else{
    }
}

resetForm(){
  this.dayBook.reset();
  this.dayBook.get('dateFrom').setErrors(null);
  this.dayBook.get('dateTo').setErrors(null);
  }

  getErrorMsg(field){
    if(this.dayBook.get('dateFrom').hasError('required')){
      return 'Enter Required Date';
    }
    else
      return '';
  }

}
