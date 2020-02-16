import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private _dialogRef: MatDialogRef<SideBarComponent>,private _router: Router) { 
    // _router.events.subscribe((event: NavigationEnd) => {
    //   this._dialogRef.close();
    // });
    
  }

  close(){
    this._dialogRef.close();
  }

  //close from child
  closeDialog(event){
    this._dialogRef.close();
  }

  ngOnInit() {
  }

}
