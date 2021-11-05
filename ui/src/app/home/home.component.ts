import {Component, OnInit} from '@angular/core';
import {Customer} from "../models/customer";
import {RestService} from "../services/rest.service";
import {ClarityIcons, trashIcon} from "@cds/core/icon";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  customers: Customer[] = [];
  customer: Customer = new Customer();
  currentTime = '';

  constructor(private restService: RestService) {
    ClarityIcons.addIcons(trashIcon);
  }

  ngOnInit() {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customer = new Customer();
    this.restService.getCustomers().subscribe(data => {
      this.customers = data;
    });
    this.restService.getTime().subscribe(data => {
      this.currentTime = data;
    });
  }

  saveCustomer(): void {
    this.restService.saveCustomer(this.customer)
      .subscribe(data => {
        this.getCustomers();
      }, error => {
        console.log(error);
      });
  }

  deleteCustomer(customer: Customer): void {
    console.log('delete: ' + customer.id);
    this.restService.deleteCustomer(customer.id)
      .subscribe(data => {
        this.getCustomers();
      }, error => {
        console.log(error);
      });
  }

}
