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

  public getCustomers(): Observable<any> {
    return this.http.get('/api/customers');
  }

  public saveCustomer(customer: Customer) {
    return this.http.post('/api/customers', customer);
  }
}
