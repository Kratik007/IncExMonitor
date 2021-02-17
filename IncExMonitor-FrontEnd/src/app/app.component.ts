import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
// import { MediaChange, MediaObserver} from '@angular/flex-layout'
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'InExMonitor';
  media:Subscription;
  constructor(private router:Router){
  }
  
  ngOnInit(){
    // this.media=this.mediaObserv.media$.subscribe(
    //   result=>{
    //   console.log(result.mqAlias);
    //   }
    // )
  }
  
  navigateTo(){
    this.router.navigate(['dashboard']);
  }

}
