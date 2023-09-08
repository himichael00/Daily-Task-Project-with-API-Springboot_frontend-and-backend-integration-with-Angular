import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-task-list',
  templateUrl: './task-list.component.html',
  styleUrls: ['./task-list.component.css']
})
export class TaskListComponent implements OnInit {

  constructor(
    private taskService: TaskService,
    private activatedRoute: ActivatedRoute,
    private toastr: ToastrService
  ) {}

  tasks: any;

  ngOnInit(): void {
    this.getTasks();

    this.activatedRoute.queryParams.subscribe((params) => {
      if (params['save'] !== undefined && params['save'] === 'true') {
        this.toastr.success('Task saved successfully');
      }
    });
  }

  getTasks() {
    this.taskService.getAll().subscribe({
      next: (data) => {
        console.log(data);
        this.tasks = data;
      },
      error: (error) => {
        this.toastr.error('Failed to get task list');
        throw error;
      },
    });
  };

  updateTasks = (id: string) => {
    this.taskService.update(id).subscribe({
      next: () => {
        this.toastr.success('task updated');
        this.getTasks();
      },
      error: (error) => {
        this.toastr.error('failed to update task');
        throw error;
      },
    });
  };
}
