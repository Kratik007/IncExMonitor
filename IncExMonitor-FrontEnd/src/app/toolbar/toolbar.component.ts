import { Component, OnInit, ViewChild, ContentChild } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { RsidebarComponent } from '../rsidebar/rsidebar.component';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css'],
})

export class ToolbarComponent implements OnInit {
@ViewChild(RsidebarComponent) rsidebar:RsidebarComponent
constructor(public router: Router,private auth:AuthenticationService) { }
  heading: string;
  width:number;
  username:string;
  ngOnInit(): void {
      this.heading="Dashboard";
      this.width=screen.width;
      this.username=sessionStorage.getItem("NAME");
  }
  
  callHome() {
    this.heading="dashboard";
    this.router.navigate(['/dashboard/menu']);
  }

  onActivate(componentRef){
    componentRef.toggleNav();
  }

  changeUsername(data){
    this.username=data;
  }

  callUpdate() {
    this.heading="profile";
    this.router.navigate(['./dashboard/update']);
  }

  logoutUser() {
    this.router.navigate(['/login']);
   }
}
