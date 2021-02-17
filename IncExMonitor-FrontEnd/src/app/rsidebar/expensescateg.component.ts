import { map } from 'rxjs/operators';
import { Component, OnInit, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DatabaseService } from '../service/database.service';
import { ExpenseCateg } from '../Entities/ExpenseCateg';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-expensescateg',
  templateUrl: './expensescateg.component.html',
  styleUrls: ['./expensescateg.component.css']
})


export class ExpensescategComponent implements OnInit, AfterViewInit {
  // @ViewChild(MatSort , { static: true }) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(private db: DatabaseService, private _snackBar: MatSnackBar, private auth: AuthenticationService) { }
  data_to_show = new Array;
  dataSource: MatTableDataSource<any>;
  tableSpinner: boolean = false;
  flag: any = null;
  ngOnInit(): void {
    if (!this.dataSource) {
      this.tableSpinner = true;
    }
  }

  ngAfterViewInit() {
    this.getDetails();
  }

  getDetails() {
    this.db.getAllExpenseCategories().then((details) => {
      let detailArray = new Array;
      let data = JSON.parse(details);
      if (data.length)
        data.forEach(element => {
          console.log(element);
          detailArray.push(element);
        });
      else
        detailArray = null;
      this.dataSource = new MatTableDataSource(detailArray);
      this.dataSource.paginator = this.paginator;
      this.tableSpinner = false;
    }).catch(err => {
      console.log(err);
      alert("Error in fetching details try again");
      this.showSpinner = false;
    })
  }

  expenseCat = new FormGroup({
    categoryname: new FormControl("", [Validators.required, Validators.minLength(3), Validators.nullValidator]),
    categorydetails: new FormControl("", [Validators.required, Validators.minLength(4), Validators.nullValidator])
  })

  columnDef: string[] = ['catname', 'catdet', 'edit', 'delete'];
  showSpinner: boolean = false;

  saveExpenseCateg() {
    if (this.expenseCat.get("categoryname").value) {
      this.showSpinner = true;
      let expenseCateg = new ExpenseCateg(0, this.expenseCat.get("categoryname").value, this.expenseCat.get("categorydetails").value, this.auth.getAuthenticatedUserId());
      if (this.flag === null) {
        console.log(expenseCateg)
        this.db.saveExpenseCategory(expenseCateg).then(data => {
          this.showSpinner = false;
          this.getDetails();
          this._snackBar.open("Category Saved", "Done", {
            duration: 3000,
            horizontalPosition: "left",
            verticalPosition: "bottom",
            panelClass: ['.mat-snack-bar-container']
          })
          this.expenseCat.reset();
          this.expenseCat.get('categoryname').setErrors(null);
          this.expenseCat.get('categorydetails').setErrors(null);
        }).catch(err => {
          console.log(err);
          alert("Error in creating Expense Category try again");
          this.showSpinner = false;
        })
      } else {
        expenseCateg.setExpenseCategoryId(this.flag);
        this.db.updateExpenseCategory(expenseCateg).then(data => {
          this.showSpinner = false;

          this.getDetails();
          this._snackBar.open("Category Updated Successfully", "Done", {
            duration: 3000,
            horizontalPosition: "left",
            verticalPosition: "bottom",
            panelClass: ['.mat-snack-bar-container']
          })
          this.flag = null;
          this.expenseCat.reset();
          this.expenseCat.get('categoryname').setErrors(null);
          this.expenseCat.get('categorydetails').setErrors(null);
        }).catch(err => {
          console.log(err)
          alert("Error in updating Expense Category try again");
          this.showSpinner = false;
        })
      }
    } else {
    }
  }

  getErrorMsg(field): string {
    if (this.expenseCat.get(field).hasError('required')) {
      return field + " Cannot Be Empty";
    }
    if (this.expenseCat.get(field).hasError) {
      return "Enter Valid " + field;
    }
    return '';
  }

  editData(event) {
    this.db.getExpenseCategoryById(event.target.id).then(data => {
      let expenseCateg = JSON.parse(data);
      this.expenseCat.get('categoryname').setValue(expenseCateg.expenseCategoryName);
      this.expenseCat.get('categorydetails').setValue(expenseCateg.expenseCategoryDetails);
      this.flag = event.target.id;
    });
  }

  deleteData(event) {
    if (confirm("Are you sure to delete this entry")) {
      this.db.deleteExpenseCategor(event.target.id).then(() => {
        this.getDetails();
        this._snackBar.open("Item Deleted", "Done", {
          duration: 3000,
          panelClass: ['mat-snack-bar-container'],
          horizontalPosition: "left",
          verticalPosition: "bottom"
        });
      })
    }
  }
}
