import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Expense } from '../Entities/Expense';
import { AuthenticationService } from '../service/authentication.service';
import { DatabaseService } from '../service/database.service';

@Component({
  selector: 'app-expenses',
  templateUrl: './expenses.component.html',
  styleUrls: ['./expenses.component.css']
})
export class ExpensesComponent implements OnInit {

  constructor(private auth:AuthenticationService,private db:DatabaseService, public snackbar: MatSnackBar) {
    const currentYear = new Date().getFullYear();
    this.minDate = new Date(currentYear - 50, 0, 1);
    this.maxDate = new Date(currentYear + 0, new Date().getMonth(), new Date().getDate());
  }

  categoryname = new Array;

  ngOnInit(): void {
    this.db.getAllExpenseCategories().then(data=>{
     let details=JSON.parse(data);
        details.forEach(element => {
          this.categoryname.push(element);
        });
    })
  }

  minDate: Date;
  maxDate: Date;
  showSpinner: boolean = false;
  expenses = new FormGroup({
    expense: new FormControl("", [Validators.required, Validators.minLength(3)]),
    expensecategory: new FormControl("", [Validators.required, Validators.minLength(4), Validators.nullValidator]),
    amount: new FormControl("", [Validators.required, Validators.pattern('(0\.((0[1-9]{1})|([1-9]{1}([0-9]{1})?)))|(([1-9]+[0-9]*)(\.([0-9]{1,2}))?)')]),
    payby: new FormControl("", [Validators.required]),
    remark: new FormControl("", [Validators.required, Validators.minLength(2)]),
    date: new FormControl("", [Validators.required])
  })

  saveExpensesDetails() {
    if (!this.expenses.invalid) {
        this.showSpinner = true;
        let expense=new Expense(0,this.expenses.get('expense').value,parseInt(this.expenses.get('amount').value),this.expenses.get('payby').value,`${this.expenses.get("date").value.getFullYear()}-${this.expenses.get("date").value.getMonth()+1}-${this.expenses.get("date").value.getDate()}`,this.expenses.get("remark").value,this.expenses.get('expensecategory').value,this.auth.getAuthenticatedUserId());  
        console.log(expense);
        this.db.saveExpenseDetails(expense).then(data=>{
          this.showSpinner=false;
          console.log(data);
          this.snackbar.open("Succesfully Saved","Done",{
            duration:3000,
            horizontalPosition:"left",
            verticalPosition:"bottom",
            panelClass:['.mat-snack-bar-container']
          })
          this.expenses.reset();
          this.expenses.get('expense').setErrors(null);
          this.expenses.get('expensecategory').setErrors(null);
          this.expenses.get('amount').setErrors(null);
          this.expenses.get('payby').setErrors(null);
          this.expenses.get('remark').setErrors(null);
          this.expenses.get('date').setErrors(null);
        }).catch(error=>{
          console.log(error);
          this.showSpinner=false;
        })
    }
  }

  getErrorMsg(field): string {
    if (this.expenses.get(field).hasError('required')) {
      return field + " cannot be empty"
    }
    if (this.expenses.get(field).hasError) {
      return "enter valid " + field;
    }
    return '';
  }

}
