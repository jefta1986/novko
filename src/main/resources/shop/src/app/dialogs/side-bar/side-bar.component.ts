import {Component, OnInit, Inject} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {Router} from '@angular/router';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private _dialogRef: MatDialogRef<SideBarComponent>,
              private _router: Router,
              @Inject(MAT_DIALOG_DATA) public categories) {
  }

  close() {
    this._dialogRef.close();
  }

  closeDialog(event) {
    this._dialogRef.close();
  }

  ngOnInit() {
  }

}
