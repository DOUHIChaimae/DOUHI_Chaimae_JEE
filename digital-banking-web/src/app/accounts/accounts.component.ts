import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountsService} from "../services/accounts.service";
import {Observable} from "rxjs";
import {AccountDetails} from "../model/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit {
  accountFormGroup!: FormGroup;
  currentPage: number = 0;
  pageSize: number = 5;
  accountObservable!: Observable<AccountDetails>;
  operationFromGroup!: FormGroup;

  constructor(private fb: FormBuilder, private accountService: AccountsService) {
  }

  ngOnInit(): void {
    this.accountFormGroup = this.fb.group({
      accountId: this.fb.control('')
    });
    this.operationFromGroup = this.fb.group({
      operationType: this.fb.control(null),
      mount: this.fb.control(0),
      description: this.fb.control(null),
      accountDestination: this.fb.control(null)
    })
  }

  handleSearchAccount() {
    let accountId: string = this.accountFormGroup.value.accountId;
    this.accountObservable = this.accountService.getAccount(accountId, this.currentPage, this.pageSize);
  }

  gotoPage(page: number) {
    this.currentPage = page;
    this.handleSearchAccount();
  }

  handleAccountOperation() {
    let accountId: string = this.accountFormGroup.value.accountId;
    let operationType = this.operationFromGroup.value.operationType;
    let mount: number = this.operationFromGroup.value.mount;
    let description: string = this.operationFromGroup.value.description;
    let accountDestination: string = this.operationFromGroup.value.accountDestination;
    if (operationType == 'DEBIT') {
      this.accountService.debit(accountId, mount, description).subscribe({
        next: (data) => {
          alert("Succes Debit");
          this.handleSearchAccount();
        }, error: (err) => {
          console.log(err);
        }

      });
    } else if (operationType == 'CREDIT') {
      this.accountService.credit(accountId, mount, description).subscribe({
        next: (data) => {
          alert("Succes Credit");
          this.handleSearchAccount();
        }, error: (err) => {
          console.log(err);
        }

      });
    } else if (operationType == 'TRANSFER') {
      this.accountService.transfer(accountId, accountDestination, mount, description).subscribe({
        next: (data) => {
          alert("Succes Transfer");
          this.handleSearchAccount();
        }, error:( err) => {
          console.log(err);
        }
      });
    }
  }
}
