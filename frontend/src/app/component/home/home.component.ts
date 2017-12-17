import { Component, OnInit } from '@angular/core';
import {RequestOptions} from "@angular/http";
import {Form} from "@angular/forms";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/map";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private isCollapsed = false;
  formData: FormData;

  constructor(private http: HttpClient) { }

  ngOnInit() {
  }

  fileChange(event) {
    let fileList: FileList = event.target.files;
    if(fileList.length > 0) {
      this.formData = new FormData();
      let file: File = fileList[0];
      this.formData.append('file', file, file.name);
    }
  }

  onUploadSubmit() {
    if (this.formData == null) {
      return;
    }
    let headers = new HttpHeaders();
    /** No need to include Content-Type in Angular 4 */
    headers.append('Content-Type', 'multipart/form-data');
    headers.append('Accept', 'application/json');
    let options = ({ headers: headers });
    this.http.post('/uploadFile', this.formData, options)
      // .map(res => res.json())
      // .catch(error => Observable.throw(error))
      .subscribe(
        data => console.log('success'),
        error => console.log(error)
      )
  }
}
