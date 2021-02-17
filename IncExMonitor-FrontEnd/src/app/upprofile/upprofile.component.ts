import { Component, OnInit, Output } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { User, UserDet } from '../Entities/User';
import { AuthenticationService } from '../service/authentication.service';
import { DatabaseService } from '../service/database.service';
// import { start } from 'repl';

@Component({
  selector: 'app-upprofile',
  templateUrl: './upprofile.component.html',
  styleUrls: ['./upprofile.component.css']
})
export class UpprofileComponent implements OnInit {
  
  constructor(private db:DatabaseService,private auth:AuthenticationService, public dialog: MatDialog, private snack: MatSnackBar) { }
  error:string;
  email:string;
  spinner: boolean = false;
  ngOnInit(): void {
    this.db.getUser(this.auth.getAuthenticatedUserId()).then((data:any)=>{
      data=JSON.parse(data)
      this.user=data;
      this.email=data.email;
      this.updateUser.get("name").setValue(`${data.fullname}`);
      this.updateUser.get("email").setValue(`${data.email}`);
      this.updateUser.get("mobile").setValue(`${data.mobile}`);
    }).catch(e=>{console.log(e)});
  }
  user:UserDet=null;
  updateUser = new FormGroup({
    name: new FormControl("", [Validators.required, Validators.pattern("[a-zA-Z]+[' ']{0,1}[a-zA-Z]+[' ']{0,1}[a-zA-Z]+"), Validators.nullValidator]),
    mobile: new FormControl("", [Validators.required, Validators.pattern("[0-9]{10}")]),
    username: new FormControl({value:this.auth.getAuthenticatedUserId(),disabled:true}, [Validators.required, Validators.pattern("[a-zA-Z0-9]{3,}")]),
    email: new FormControl("", [Validators.required, Validators.email]),
    password: new FormControl("", [Validators.required, Validators.nullValidator])
  })
  show: boolean = false;
  updateUserDet() {
    if (!this.updateUser.invalid) {
      this.spinner = true      
      let user=new User(this.auth.getAuthenticatedUserId(),this.updateUser.get("password").value,this.updateUser.get('name').value,
      this.updateUser.get('email').value,
      this.updateUser.get('mobile').value
      )
      if(this.email!= this.updateUser.get('email').value){
        this.db.updateUserWithEmail(user).then((data:Response)=>{
          location.reload();
          this.snack.open("User Details Updated Succesfully", "Done", {
               duration: 3000,
               horizontalPosition: "left",
               verticalPosition: "bottom",
               panelClass: ['.mat-snack-bar-container']
             })
             this.spinner=false;      
        }).catch(err=>{
          if(err.status==409){
            alert("Email Already Exist");
          }
          else{
            alert("Internal Server Error");
          }
          this.spinner=false;
        })
      }else{
      this.db.updateUser(user).then((data:any)=>{
       console.log(data);
       this.auth.setAuthenticatedUserName(JSON.parse(data).fullname);
       location.reload();
       this.snack.open("User Details Updated Succesfully", "Done", {
            duration: 3000,
            horizontalPosition: "left",
            verticalPosition: "bottom",
            panelClass: ['.mat-snack-bar-container']
          })
          this.spinner=false;
     }).catch(err=>{
       console.log(err);
       this.spinner=false;
     })
    }
    }
  }

  getErrorMsg(fieldname) {
    let form = this.updateUser;
    if (form.get(fieldname).hasError('required')) {
      return (fieldname + " cannot be empty");
    }
    if (fieldname == "email") {
      return form.get("" + fieldname).hasError('email') ? "Please enter valid " + fieldname : "";
    }
    return form.get("" + fieldname).hasError('pattern') ? "Please enter valid " + fieldname : "";
  }

}
