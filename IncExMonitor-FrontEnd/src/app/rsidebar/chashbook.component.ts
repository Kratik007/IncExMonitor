import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { DatabaseService } from '../service/database.service';
import { AuthenticationService } from '../service/authentication.service';
// export interface element{
//   sno:number;
//   date:Date;
//   amount:number;
//   pay_recieve:string;
// }

// const ELEMENT_DATA:element[]=[
//   {sno:1,date:new Date('2000/03/12'),amount:3748,pay_recieve:'pay'},
//   {sno:2,date:new Date('2000/03/13'),amount:3428,pay_recieve:'recieve'},
//   {sno:3,date:new Date('2000/03/14'),amount:2348,pay_recieve:'pay'},
//   {sno:4,date:new Date('2000/03/15'),amount:6538,pay_recieve:'pay'},
//   {sno:5,date:new Date('2000/03/16'),amount:6628,pay_recieve:'pay'},
//   {sno:6,date:new Date('2000/03/17'),amount:1128,pay_recieve:'recieve'},
//   {sno:7,date:new Date('2000/03/18'),amount:3428,pay_recieve:'recieve'},
//   {sno:8,date:new Date('2000/03/19'),amount:9823,pay_recieve:'pay'},
//   {sno:9,date:new Date('2000/03/20'),amount:3632,pay_recieve:'recieve'},
//   {sno:10,date:new Date('2000/03/21'),amount:4545,pay_recieve:'pay'},
//   {sno:11,date:new Date('2000/03/22'),amount:5417,pay_recieve:'recieve'},
//   {sno:12,date:new Date('2000/03/23'),amount:3642,pay_recieve:'pay'},
  
// ]

@Component({
  selector: 'app-chashbook',
  templateUrl: './chashbook.component.html',
  styleUrls: ['./chashbook.component.css']
})
export class ChashbookComponent implements OnInit {
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  showSpinner: boolean = false;
  minDate;
  maxDate;
  tableSpinner:boolean=false;
  closingBal:number=0;
  dataSource:MatTableDataSource<null>;
  cashbooksDet=new Array
  constructor(private db:DatabaseService,private auth:AuthenticationService) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
     }
     columnDef: string[] = ['date', 'amount', 'pay_recieve'];
  width: number;
  ngOnInit(): void {
    this.width = screen.width;
  }

  ngAfterViewInit():void{
    this.getData();
  }

  cashBook = new FormGroup({
    dateFrom: new FormControl("",[Validators.required]),
    dateTo: new FormControl("",[Validators.required]),
  })


  
  getData(datefrom=null, dateto=null) {
    let books=new Array();
    this.dataSource=null;
    if (datefrom > dateto) {
      alert("Date from cannot be greater than Date to");
    }
    else if (datefrom == null && dateto == null) {
      this.tableSpinner = true;
      this.closingBal = 0;
      this.db.getAllCashBookDetails(this.auth.getAuthenticatedUserId()).then((data: string) => {
        this.cashbooksDet = JSON.parse(data);
        this.cashbooksDet.forEach(item => {
          if (item.pay_receive=="PAY") {
            this.closingBal -= item.amount;
          }
          else {
            this.closingBal += item.amount;
          }
        })
        this.tableSpinner = false
        this.showSpinner = false
        this.dataSource = new MatTableDataSource(this.cashbooksDet);
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator;
      }).catch(err => {
        console.log(err);
          this.tableSpinner = false;
          alert("Error in finding details");
      })
    } else {
      this.tableSpinner = true;
      this.closingBal = 0;
      this.db.getCashBookBetweenDates(this.auth.getAuthenticatedUserId(), datefrom, dateto).then((data: string) => {
        this.cashbooksDet = JSON.parse(data);
        this.cashbooksDet.forEach(item => {
          if (item.pay_receive == "PAY") {
            this.closingBal -= item.amount;
          }
          else {
            this.closingBal += item.amount;
          }
          })
          this.tableSpinner = false;
          this.showSpinner = false;
          this.dataSource = new MatTableDataSource(this.cashbooksDet);
          this.dataSource.sort = this.sort
          this.dataSource.paginator = this.paginator;
        
      }).catch(err => {
        alert("Error in finding details");
          this.tableSpinner = false;
          console.log(err);
      })
    }
}

  findRecord(){
    if(!this.cashBook.invalid){
    let datefrom = `${this.cashBook.get('dateFrom').value.getFullYear()}-${this.cashBook.get('dateFrom').value.getMonth()+1}-${this.cashBook.get('dateFrom').value.getDate()}`;
    let dateto = `${this.cashBook.get('dateTo').value.getFullYear()}-${this.cashBook.get('dateTo').value.getMonth()+1}-${this.cashBook.get('dateTo').value.getDate()}`;
    this.getData(datefrom,dateto);
    }
    else{
      alert("Please Select date");
    }
}

  resetForm(){
    this.cashBook.reset();
    this.cashBook.get('dateFrom').setErrors(null);
    this.cashBook.get('dateTo').setErrors(null);
    }

  getErrorMsg(field){
    if(this.cashBook.get(field).hasError('required')){
      return "Select Required date"
    }
    else 
    return '';
  }

}
