import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shop';

  public get showNavigation(): boolean {
    return !this.router.url.includes('/login');
  }

  constructor(private router: Router) {
  }

}
