import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {CategoryService} from '../services/category.service';
import {Router} from '@angular/router';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-side-panel',
  templateUrl: './side-panel.component.html',
  styleUrls: ['./side-panel.component.css']
})
export class SidePanelComponent implements OnInit {

  @Input('categories') categories;
  @Output() routeChange = new EventEmitter();

  constructor(private _categoryService: CategoryService,
              private _router: Router,
              private _authService: AuthService) {
  }

  onRouteChanged() {
    this.routeChange.emit(true);
  }

  ngOnInit() {
  }

}
