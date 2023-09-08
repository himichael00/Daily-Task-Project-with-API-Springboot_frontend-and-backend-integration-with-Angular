import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private httpClient: HttpClient) {}

  getAll = (): Observable<any> => {
    return this.httpClient.get(`${environment.baseUrl}/task`, {
      responseType: 'json',
    });
  };

  add = (body: any): Observable<any> => {
    return this.httpClient.post(`${environment.baseUrl}/task`, { ...body });
  };

  update = (id: string): Observable<any> => {
    return this.httpClient.put(`${environment.baseUrl}/task`, { id: id });
  };
}

// getAll = (): Observable<any> => {
//   return this.httpClient.get(environment.baseUrl+'/task', {
//     responseType: 'json',
//   });
// };

// add = (body: any): Observable<any> => {
//   return this.httpClient.post(environment.baseUrl+'/task', { ...body });
// };

// update = (id: string): Observable<any> => {
//   return this.httpClient.put(environment.baseUrl+'/task', { id: id });
// };
