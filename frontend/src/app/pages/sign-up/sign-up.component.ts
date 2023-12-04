import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {SignUpRequest} from "../../services/auth/sign-up.request";
import {tap} from "rxjs";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {
  signUpForm: FormGroup;

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
  ) {
    this.signUpForm = formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  onSubmit() {
    this.authService.signUp(this.map(this.signUpForm))
      .pipe((tap(tap => console.log(JSON.stringify(tap)))))
      .subscribe(() => {
        this.router.navigate(["blog"])
          .then(r => alert(JSON.stringify(r)));
      });
  }

  map(form: FormGroup): SignUpRequest {
    return {
      firstName: form.get("firstName")?.value,
      lastName: form.get("lastName")?.value,
      email: form.get("email")?.value,
      password: form.get("password")?.value,
    } as SignUpRequest;
  }
}
