import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { CartComponent } from './cart/cart.component';
import { AdminComponent } from './admin/admin.component';
import { LoginGuard } from './guards/login.guard';
import { AuthGuardUserGuard } from './guards/auth-guard-user.guard';
import { AuthGuardAdminGuard } from './guards/auth-guard-admin.guard';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent,canActivate:[LoginGuard]},
  { path: 'home', component: HomeComponent,canActivate:[AuthGuardUserGuard] },
  { path: 'cart', component: CartComponent ,canActivate:[AuthGuardUserGuard]},
  { path: 'admin', component: AdminComponent ,canActivate:[AuthGuardAdminGuard]},
  { path: 'registration', component: RegistrationComponent ,canActivate:[AuthGuardAdminGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
