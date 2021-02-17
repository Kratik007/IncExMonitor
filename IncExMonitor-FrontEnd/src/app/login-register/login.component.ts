import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { AuthenticationService } from '../service/authentication.service';
import { Credentials } from '../Entities/Credentials';
import { Response } from '../Entities/Response';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  error: string;
  constructor(private auth:AuthenticationService, private router: Router, public dialog: MatDialog, private http:HttpClient) {

  }

  ngOnInit(): void { }

  loginForm = new FormGroup({
    username: new FormControl("", Validators.required),
    password: new FormControl("", Validators.required),
    check: new FormControl()
  })
  showSpinner: boolean = false;
  signIn() {
    this.error = null;
    this.showSpinner = true;
    
    this.auth.authenticateUser(new Credentials(this.loginForm.get('username').value, this.loginForm.get('password').value)).then((data:Response)=>{
      sessionStorage.setItem("TOKEN","Bearer "+data.jwt);
      sessionStorage.setItem("USERID",data.userid);
      sessionStorage.setItem("NAME",data.name);
      this.auth.getGreated().then(da=>{
        this.showSpinner=false;
        this.router.navigate(['/dashboard/menu/expensecategory']);
      }).catch(err=>{
        this.error="Error loading user details";
        this.showSpinner=false;
      })
    }).catch(err=>{
      this.error="Error loging in user check username password and try again";
      this.showSpinner=false;
    });
  }

  getErrorMsg(field) {
    return this.loginForm.get(field).hasError('required') ? field + " Cannot Be Empty" : "";
  }

}
