import { Component, OnInit, ViewChild } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { DatabaseService } from '../service/database.service';
import { AuthenticationService } from '../service/authentication.service';

// export interface elements {
//   sno: number;
//   date: Date;
//   amount: number;
//   pay_recieve: string;
// }

// const ELEMENT_DATA: elements[] = [
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
//   // { 1, 2000/2/1, 7384, 'PAY'}
// ]

@Component({
  selector: 'app-bankbook',
  templateUrl: './bankbook.component.html',
  styleUrls: ['./bankbook.component.css']
})
export class BankbookComponent implements OnInit {
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  showSpinner: boolean = false;
  minDate;
  maxDate;
  tableSpinner: boolean = false;
  closingBal: number = 0;
  dataSource: MatTableDataSource<null>;
  bankbooksDet = new Array
  constructor(private db: DatabaseService, private auth: AuthenticationService) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
    // this.closingBal = 7384.43;
  }



  columnDef: string[] = ['date', 'amount', 'pay_recieve'];
  width: number;
  ngOnInit(): void {
    this.width = screen.width;
    this.getData();
  }

  bankBook = new FormGroup({
    dateFrom: new FormControl("", [Validators.required]),
    dateTo: new FormControl("", [Validators.required]),
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
      this.db.getAllBankBookDetails(this.auth.getAuthenticatedUserId()).then((data: string) => {
        this.bankbooksDet = JSON.parse(data);
        this.bankbooksDet.forEach(item => {
          if (item.pay_receive=="PAY") {
            this.closingBal -= item.amount;
          }
          else {
            this.closingBal += item.amount;
          }
        })
        this.tableSpinner = false
        this.dataSource = new MatTableDataSource(this.bankbooksDet);
        this.dataSource.sort = this.sort
        this.dataSource.paginator = this.paginator;
      }).catch(err => {
        console.log(err);
      this.tableSpinner = true;
      alert("Error in finding details");
      })
    } else {
      this.showSpinner = true;
      this.tableSpinner = true;
      this.closingBal = 0;
      this.db.getBankBookBetweenDates(this.auth.getAuthenticatedUserId(), datefrom, dateto).then((data: string) => {
        this.bankbooksDet = JSON.parse(data);
        this.bankbooksDet.forEach(item => {
          if (item.pay_receive=="PAY") {
            this.closingBal -= item.amount;
          }
          else {
            this.closingBal += item.amount;
          }
          })
          this.tableSpinner = false;
          this.showSpinner = false;
          this.dataSource = new MatTableDataSource(this.bankbooksDet);
          this.dataSource.sort = this.sort
          this.dataSource.paginator = this.paginator;
        
      }).catch(err => {
        alert("Error in finding details");
        console.log(err);
        this.tableSpinner = false;
        this.showSpinner = false;
      })
    }
  }

  findRecord() {
    if (!this.bankBook.invalid) {
      this.bankbooksDet = new Array;
      let datefrom = `${this.bankBook.get('dateFrom').value.getFullYear()}-${this.bankBook.get('dateFrom').value.getMonth()+1}-${this.bankBook.get('dateFrom').value.getDate()}`;
      let dateto = `${this.bankBook.get('dateTo').value.getFullYear()}-${this.bankBook.get('dateTo').value.getMonth()+1}-${this.bankBook.get('dateTo').value.getDate()}`;
      this.getData(datefrom, dateto);
    }
  }

  resetForm() {
    this.bankBook.reset();
    this.bankBook.get('dateFrom').setErrors(null);
    this.bankBook.get('dateTo').setErrors(null);
    this.getData();
  }

  getErrorMsg(field) {
    if (this.bankBook.get(field).hasError('required')) {
      return 'Select Required Date';
    }
    return '';
  }

}
