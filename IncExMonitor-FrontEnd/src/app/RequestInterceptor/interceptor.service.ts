import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';

@Injectable({
  providedIn: 'root'
})

export class InterceptorService implements HttpInterceptor {
  constructor(private auth:AuthenticationService){}
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(this.auth.getAuthenticationToken() && this.auth.getAuthenticatedUserId){
      req = req.clone({
      setHeaders: {
        "ACCESS-CONTROL-ALLOW-ORIGIN":"*",
        "Access-Control-Allow-Methods":"GET, POST, OPTIONS, DELETE, PUT", 
        'Accept': 'application/json',
        'Content-Type':'application/json',
        'Authorization': `${this.auth.getAuthenticationToken()}`,
      },
      'responseType':'text'
    });
  }
    return next.handle(req);

}

}
