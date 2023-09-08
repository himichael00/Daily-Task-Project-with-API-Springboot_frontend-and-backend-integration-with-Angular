import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TaskListComponent } from './components/task/task-list/task-list.component';
import { TaskAddComponent } from './components/task/task-add/task-add.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'tasks'},
  { path: 'tasks', component: TaskListComponent},
  { path: 'add', component: TaskAddComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
