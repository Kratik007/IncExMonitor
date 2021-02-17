import { Injectable } from '@angular/core';
import { CanDeactivate } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './service/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class DeAuthPageGuard implements CanDeactivate<AuthenticationService> {
  constructor(private auth:AuthenticationService){}
  canDeactivate():boolean{
    if(confirm("Are you sure you want to Logout ?"))
    {
      this.auth.logoutUser();
      return true;
    } 
    else return false;
  }

}
