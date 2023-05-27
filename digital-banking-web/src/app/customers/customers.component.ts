import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../services/customer.service";
import {catchError, map, Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import Swal from 'sweetalert2';


@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit {
  customers!: Observable<Array<Customer>>;
  errorMessage!: string;
  searchFormGroup: FormGroup | undefined;

  constructor(private customerService: CustomerService, private fb: FormBuilder, private router: Router) {
  }

  ngOnInit(): void {
    this.searchFormGroup = this.fb.group({
      keyword: this.fb.control("")
    });
    this.handleSearchCustomers();
  }

  handleSearchCustomers() {
    let kw = this.searchFormGroup?.value.keyword;
    this.customers = this.customerService.searchCustomers(kw).pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    );
  }

  handleDeleteCustomer(c: Customer) {
    Swal.fire({
      title: 'Are you sure?',
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#d33',
      cancelButtonColor: '#109406',
      confirmButtonText: 'Yes',
      cancelButtonText: 'No',
    }).then((result) => {
      if (result.isConfirmed) {
        this.customerService.deleteCustomer(c.id).subscribe({
          next: (resp) => {
            this.customers = this.customers.pipe(
              map((data) => {
                let index = data.indexOf(c);
                data.slice(index, 1);
                return data;
              })
            );
          },
          error: (err) => {
            console.log(err);
          },
        });
      } else if (result.isDismissed) {
      }
    });
  }

  handleCustomerAccounts(customer: Customer) {
    console.log("clicked ")
    this.router.navigateByUrl("/customer-accounts/" + customer.id);
  }
}
