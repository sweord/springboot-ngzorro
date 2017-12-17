import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";
import {NzMessageService} from "ng-zorro-antd";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  validateForm: FormGroup;
  public username: string;
  public password: string;
  errorMsg = '';

  constructor(private fb: FormBuilder,
              private http: HttpClient,
              private router: Router,
              private activeRoute: ActivatedRoute,
              private message: NzMessageService) {
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      userName: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true],
    });
  }

  submitForm() {
    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
    }
    this.onLogin();
  }

  onLogin() {
    const url = '/auth';
    const params = new URLSearchParams();
    params.set('username', this.username);
    params.set('password', this.password);

    const header = {
      headers: new HttpHeaders().set('Content-Type', 'application/x-www-form-urlencoded'),
    };

    this.http.post(url, params.toString(), header).subscribe(
      data => {
        console.log(data);
        if (data != null && data === 'success') {
          this.router.navigateByUrl('/');
        } else {
          this.message.info("Login failed.")
        }
      },
      err => {
        console.log('error: ' + err.errorMsg);
      }
    );
  }
}
