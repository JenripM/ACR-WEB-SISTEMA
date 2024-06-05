import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class AppSideLoginComponent {
  email: string = "";
  password: string = "";

  constructor(private router: Router, private http: HttpClient, private authService: AuthService) {}

  login() {
    console.log(this.email);
    console.log(this.password);

    let bodyData = {
      email: this.email,
      password: this.password,
    };

    this.http.post("http://localhost:8080/api/v1/user/login", bodyData).subscribe((resultData: any) => {
      console.log(resultData);

      if (resultData.message == "Email not exists") {
        alert("Email not exists");
      } else if (resultData.message == "Login Success") {
        this.authService.login(resultData.token); // Guarda el token de autenticación
        this.router.navigateByUrl('/dashboard');
      } else {
        alert("Incorrect Email and Password not match");
      }
    });
  }
}
