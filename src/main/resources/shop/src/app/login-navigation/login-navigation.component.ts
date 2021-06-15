import {ChangeDetectorRef, Component, EventEmitter, OnDestroy, OnInit, Output} from '@angular/core';
import {User} from '../data/user';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../services/auth.service';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';

@Component({
  selector: 'app-login-navigation',
  templateUrl: './login-navigation.component.html',
  styleUrls: ['./login-navigation.component.css']
})
export class LoginNavigationComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  public badLogin = false;

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  @Output() userLoggedIn: EventEmitter<void> = new EventEmitter<void>();

  constructor(private formBuilder: FormBuilder,
              private _authService: AuthService,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

  public login(): void {
    const user = new User(this.loginForm.get('username')?.value, this.loginForm.get('password')?.value);
    this.userLoggedIn.emit();
    this._authService.authenticate(user, false).subscribe(
      data => {
      },
      err => {
        this.badLogin = true;
      }
    );
  }


}
