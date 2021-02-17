import { Component, OnInit, ViewChild, AfterViewInit, ElementRef } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DatabaseService } from '../service/database.service';
import { AuthenticationService } from '../service/authentication.service';
import { IncomeCateg } from '../Entities/IncomeCateg';

@Component({
  selector: 'app-incomecateg',
  templateUrl: './incomecateg.component.html',
  styleUrls: ['./incomecateg.component.css']
})
export class IncomecategComponent implements OnInit {
  // @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild('resetButton') resetButton: ElementRef<HTMLElement>;

  constructor(private db: DatabaseService, private auth: AuthenticationService, private _snackBar: MatSnackBar) { }
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

  incomeCat = new FormGroup({
    categoryname: new FormControl("", [Validators.required, Validators.minLength(3), Validators.nullValidator]),
    categorydetails: new FormControl("", [Validators.required, Validators.minLength(4), Validators.nullValidator])
  })

  columnDef: string[] = ['catname', 'catdet', 'edit', 'delete'];
  showSpinner: boolean = false;

  saveIncomeCateg() {
    if (this.incomeCat.get("categoryname").value) {
      this.showSpinner = true;
      let incomeCateg = new IncomeCateg(0, this.incomeCat.get("categoryname").value, this.incomeCat.get("categorydetails").value, this.auth.getAuthenticatedUserId());
      if (this.flag === null) {
        console.log(incomeCateg)
        this.db.saveIncomeCategory(incomeCateg).then(data => {
          this.showSpinner = false;
          this.getDetails();
          this._snackBar.open("Category Saved", "Done", {
            duration: 3000,
            horizontalPosition: "left",
            verticalPosition: "bottom",
            panelClass: ['.mat-snack-bar-container']
          })
          this.incomeCat.reset();
          this.incomeCat.get('categoryname').setErrors(null);
          this.incomeCat.get('categorydetails').setErrors(null);
        }).catch(err => {
          console.log(err);
          alert("Error in creating Income Category try again");
          this.showSpinner = false;
        })
      } else {
        incomeCateg.setIncomeCategoryId(this.flag);
        this.db.updateIncomeCategory(incomeCateg).then(data => {
          this.showSpinner = false;

          this.getDetails();
          this._snackBar.open("Category Updated Successfully", "Done", {
            duration: 3000,
            horizontalPosition: "left",
            verticalPosition: "bottom",
            panelClass: ['.mat-snack-bar-container']
          })
          this.flag = null;
          this.incomeCat.reset();
          this.incomeCat.get('categoryname').setErrors(null);
          this.incomeCat.get('categorydetails').setErrors(null);
        }).catch(err => {
          console.log(err)
          alert("Error in updating Income Category try again");
          this.showSpinner = false;
        })
      }
    } else {
    }
  }


  getErrorMsg(field): string {
    if (this.incomeCat.get(field).hasError('required')) {
      return field + " Cannot Be Empty";
    }
    if (this.incomeCat.get(field).hasError) {
      return "Enter Valid " + field;
    }
    return '';
  }

  editData(event) {
    this.db.getIncomeCategoryById(event.target.id).then(data => {
      let incomeCateg = JSON.parse(data);
      this.incomeCat.get('categoryname').setValue(incomeCateg.incomeCategoryName);
      this.incomeCat.get('categorydetails').setValue(incomeCateg.incomeCategoryDetails);
      this.flag = event.target.id;
    });
  }

  getDetails() {
    this.db.getAllIncomeCategories().then((details) => {
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


  deleteData(event) {
    if (confirm("Are you sure to delete this entry")) {
      this.db.deleteIncomeCategor(event.target.id).then(() => {
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
