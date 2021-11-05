import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Customer} from "../models/customer";

@Injectable({
  providedIn: 'root'
})
export class RestService {

  constructor(private http: HttpClient) {
  }

  public getCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>('/api/customer');
  }

  public saveCustomer(customer: Customer) {
    return this.http.post('/api/customer', customer);
  }

  public deleteCustomer(id: any): Observable<any> {
    return this.http.delete('/api/customer/' + id);
  }

  public getTime(): Observable<string> {
    return this.http.get<string>('/api/time');
  }
}
