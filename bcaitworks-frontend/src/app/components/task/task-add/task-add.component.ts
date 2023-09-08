import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.css']
})
export class TaskAddComponent implements OnInit {


  addForm!: FormGroup;
  form: any;
  constructor (
    private taskService: TaskService,
    private router: Router,
    private toastr: ToastrService,
  ) {
    this.form = {
      title: '',
    };
  }

  ngOnInit(): void {
    this.addForm = new FormGroup ({
      title: new FormControl('', Validators.required),
    });
  }

  addTask() {
    if (this.addForm.invalid) {
      this.toastr.error('Task cannot be empty');
      return;
    }

  this.form.title = this.addForm.get('title')?.value;
  this.taskService.add(this.form).subscribe({
    next: () =>
      this.router.navigate(['/'], { queryParams: { save: 'false'} }),
    error: () => this.toastr.error('failed to save task, try again'),
  });
  }
}
