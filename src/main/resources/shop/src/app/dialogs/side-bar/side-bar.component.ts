import {Component, OnInit, Inject} from '@angular/core';
import {Router} from '@angular/router';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Category} from '../../data/category';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  constructor(private _dialogRef: MatDialogRef<SideBarComponent>,
              private _router: Router,
              @Inject(MAT_DIALOG_DATA) public categories: Category[]) {
  }

  close() {
    this._dialogRef.close();
  }

  closeDialog() {
    this._dialogRef.close();
  }

  ngOnInit() {
  }

}
