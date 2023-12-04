import {Routes} from '@angular/router';
import {BlogComponent} from "./pages/blog/blog.component";
import {AboutMeComponent} from "./pages/about-me/about-me.component";
import {ResumeComponent} from "./pages/resume/resume.component";
import {SignUpComponent} from "./pages/sign-up/sign-up.component";
import {LoginComponent} from "./pages/login/login.component";

export const routes: Routes = [
  {path: 'blog', component: BlogComponent},
  {path: 'about-me', component: AboutMeComponent},
  {path: 'resume', component: ResumeComponent},
  {path: 'sign-up', component: SignUpComponent},
  {path: 'login', component: LoginComponent},
];
