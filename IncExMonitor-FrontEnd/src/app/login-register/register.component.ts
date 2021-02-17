import { Component, OnInit } from '@angular/core';
import { Validators, FormControl, FormGroup, AbstractControl, FormGroupDirective, NgForm, ValidatorFn } from '@angular/forms';
import { Router } from '@angular/router';
import {MatSnackBar} from "@angular/material/snack-bar"
import { User } from '../Entities/User';
import { DatabaseService } from '../service/database.service';
import { MessageResponse } from '../Entities/MessageResponse';
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {

  constructor(private router: Router, private snackbar:MatSnackBar,private db:DatabaseService) { }
  ngOnInit(): void {
    this.error=null
  }
  // tncCheck:boolean=false;
  registerForm = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z]+[' ']{0,1}[a-zA-Z]+[' ']{0,1}[a-zA-Z]+"), Validators.nullValidator]),
    email: new FormControl("", [Validators.required, Validators.email, Validators.nullValidator]),
    mobile: new FormControl("", [ Validators.pattern("[0-9]{10}"), Validators.nullValidator]),
    username: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z0-9]{3,}")]),
    password: new FormControl("", [Validators.required, Validators.nullValidator]),
    tncChecked: new FormControl("")
  })

  error:string;
  tncCheck = false;
  showSpinner:boolean=false;
  registerUser(){
    this.showSpinner=true;
    let user=new User(this.registerForm.get("username").value,this.registerForm.get("password").value,this.registerForm.get("name").value,this.registerForm.get("email").value,this.registerForm.get("mobile").value);
    // this.authservice.registerUserWithEmail(this.registerForm.get('email').value,this.registerForm.get('password').value,user).then(()=>{
    //   this.showSpinner=false;
    //   this.snackbar.open("Registered Succesfully","Done",{
    //     duration:3000,
    //     horizontalPosition:"left",
    //     verticalPosition:"bottom",
    //     panelClass:['.mat-snack-bar-container']
    //   })
    // });
    this.db.registerUser(user).then((data:MessageResponse)=>{
      this.showSpinner=false;
      this.snackbar.open("Registered Succesfully","Done",{
        duration:3000,
        horizontalPosition:"left",
        verticalPosition:"bottom",
        panelClass:['.mat-snack-bar-container']
    })
  }).catch(err=>{
    console.log(err);
    this.error="Error is registering user";
    this.showSpinner=false;
  })
}
  
  show: boolean = false;

  showPass() {
    this.show = true;
  }

  hidePass() {
    this.show = false;
  }

  getErrorMsg(fieldname) {
    if (this.registerForm.get(fieldname).hasError('required')) {
      return (fieldname + " cannot be empty");
    }
    if (fieldname == "email") {
      return this.registerForm.get("" + fieldname).hasError('email') ? "Please enter valid " + fieldname : "";
    }
    return this.registerForm.get("" + fieldname).hasError('pattern') ? "Please enter valid " + fieldname : "";
  }
  
}
