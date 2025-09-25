import { Routes } from '@angular/router';
import {Home} from './components/home/home';
import {Register} from './pages/register/register';
import {Login} from './pages/login/login';
import { AboutUs } from './pages/about-us/about-us';

export const routes: Routes = [
  {path: "", component: Home},
  {path: "register", component:Register},
  {path: "login", component:Login},
  {path: "about us", component:AboutUs}
];
