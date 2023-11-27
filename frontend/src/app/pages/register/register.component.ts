import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, FormsModule, Validators} from "@angular/forms";
import {AuthenticationService} from "../../services/authentication/authentication.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  registerForm: FormGroup;

  constructor(authenticationService: AuthenticationService,
              formBuilder: FormBuilder,
  ) {

    this.registerForm = formBuilder.group({
      firstName: ["", Validators.required],
      lastName: ["", Validators.required],
      email: ["", Validators.required],
      password: ["", Validators.required],
    });
  }

  register($event: any) {
    this.authenticationService.register(null);
  }
}
