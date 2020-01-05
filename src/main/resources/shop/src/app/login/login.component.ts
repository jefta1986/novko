import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });

  constructor(private formBuilder: FormBuilder,private http:HttpClient) { }

  ngOnInit() {
    this.http.get("https://jsonplaceholder.typicode.com/posts").subscribe(res=>{
      console.log("rest test");
      console.log(res);
    })
  }

  login(loginForm) {
    console.log(loginForm);
  }

}
