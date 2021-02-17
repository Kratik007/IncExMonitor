import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Credentials } from '../Entities/Credentials';
import { Response } from '../Entities/Response';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  url=environment.URL;
  constructor(private http:HttpClient) { }

  authenticateUser( cred:Credentials){
    console.log(cred);
    let options = {
      headers: new HttpHeaders()
    }
    options.headers.append("ACCESS-CONTROL-ALLOW-ORIGIN","*");
    options.headers.append("Access-Control-Allow-Methods","GET, POST, OPTIONS, DELETE, PUT");
   return this.http.post<Response>(`${this.url}authenticate`,cred,options).toPromise();
  }

  getGreated(){
    let options = {
      headers: new HttpHeaders()
    }
    alert(this.getAuthenticationToken());
    options.headers.append("Authorization",this.getAuthenticationToken());
    return this.http.get<string>(`${this.url}hello`,options).toPromise();
  }

  getAuthenticatedUserId(){
    return sessionStorage.getItem("USERID");
  }
  getAuthenticationToken(){
    return sessionStorage.getItem("TOKEN");
  }
  getAuthenticatedUserName(){
    return sessionStorage.getItem("NAME");
  }
  setAuthenticatedUserName(fullname){
    sessionStorage.setItem("NAME",fullname);
  }

  logoutUser(){
    sessionStorage.removeItem("USERID");
    sessionStorage.removeItem("TOKEN");
    sessionStorage.removeItem("NAME");
  }

}
