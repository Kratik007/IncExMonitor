import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Income } from '../Entities/Income';
import { AuthenticationService } from '../service/authentication.service';
import { DatabaseService } from '../service/database.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css']
})
export class IncomeComponent implements OnInit {

  minDate;
  maxDate;
  categoryname = new Array();
  showSpinner: boolean = false;
  constructor(private db:DatabaseService,private auth:AuthenticationService,public snackbar: MatSnackBar) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
  }
  ngOnInit(): void {
    this.db.getAllIncomeCategories().then(data=>{
      let details=JSON.parse(data);
         details.forEach(element => {
           this.categoryname.push(element);
         });
     })
  }

  income = new FormGroup({
    incomes: new FormControl("", [Validators.required, Validators.minLength(3)]),
    incomecategory: new FormControl("", [Validators.required, Validators.minLength(4), Validators.nullValidator]),
    amount: new FormControl("", [Validators.required, Validators.pattern('(0\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\.([0-9]{1,2}))?)')]),
    receiveby: new FormControl("", [Validators.required]),
    remark: new FormControl("", [Validators.required, Validators.minLength(2)]),
    date: new FormControl("", [Validators.required])
  })

  saveIncomeDetails() {
    if (!this.income.invalid) {
      this.showSpinner = true;
      let income = new Income(0, this.income.get('incomes').value, parseInt(this.income.get('amount').value), this.income.get('receiveby').value, `${this.income.get("date").value.getFullYear()}-${this.income.get("date").value.getMonth() + 1}-${this.income.get("date").value.getDate()}`, this.income.get("remark").value, this.income.get('incomecategory').value, this.auth.getAuthenticatedUserId());
      console.log(income);
      this.db.saveIncomeDetails(income).then(data => {
        this.showSpinner = false;
        console.log(data);
        this.snackbar.open("Succesfully Saved", "Done", {
          duration: 3000,
          horizontalPosition: "left",
          verticalPosition: "bottom",
          panelClass: ['.mat-snack-bar-container']
        })
        this.income.reset();
        this.income.get('incomes').setErrors(null);
        this.income.get('incomecategory').setErrors(null);
        this.income.get('amount').setErrors(null);
        this.income.get('receiveby').setErrors(null);
        this.income.get('remark').setErrors(null);
        this.income.get('date').setErrors(null);
      }).catch(error => {
        console.log(error);
        this.showSpinner = false;
      })
    }
}

getErrorMsg(field): string {
  if (this.income.get(field).hasError('required')) {
    return field + " cannot be empty"
  }
  if (this.income.get(field).hasError) {
    return "enter valid " + field;
  }
  return '';
}

}
