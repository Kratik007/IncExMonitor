import { Component, OnInit, ViewChild,} from '@angular/core';
import { Router } from '@angular/router';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-rsidebar',
  templateUrl: './rsidebar.component.html',
  styleUrls: ['./rsidebar.component.css']
})
export class RsidebarComponent implements OnInit{
  @ViewChild('sidenav') sidenav:MatSidenav;

  modeofopen:string;
  open:boolean;

  height:number;
  width:number;

  constructor(private router:Router) { 
  }
   
    ngOnInit(): void {
      this.width=screen.width;
        if(this.width>=768){
          this.modeofopen="side";
          this.open=true;
        }
        else{
          this.open=false;
          this.modeofopen="over";
          if(this.router.url.indexOf("menu")+4===this.router.url.length){
            this.router.navigate(['./dashboard/menu/expensecategory']);
          }
        }
      }

  navigateTo(source){
    this.router.navigate([source]);
  }

  toggleNav(){
    this.sidenav.toggle();
  }
}
