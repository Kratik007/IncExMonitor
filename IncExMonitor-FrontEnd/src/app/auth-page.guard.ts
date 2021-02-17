import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from './service/authentication.service';


@Injectable({
  providedIn: 'root'
})

export class AuthPageGuard implements CanActivate {
  constructor(private router:Router,private auth:AuthenticationService){
  }
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    if(this.auth.getAuthenticationToken()){
      return true;
    }
    else
    {
      alert("Seems you have not logged in yet!!!");
      this.router.navigate(['/login']);
      return false;
    }
  }
}
