import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';
import { Vendita } from '../model/vendita';
import { AskConfirmService } from '../service/ask-confirm.service';
import { CassiereService } from '../service/cassiere.service';

@Component({
  selector: 'app-vendita',
  templateUrl: './vendita.component.html',
  styleUrls: ['./vendita.component.scss']
})
export class VenditaComponent implements OnInit {

  private _isSearchFormVisible!: boolean;

  private _searchForm!: FormGroup;

  private _vendite !: Vendita[];

  constructor(private builer: FormBuilder,
    private cassiereService: CassiereService,
    private authenticationService: AuthenticationService,
    private router: Router,
    private askService: AskConfirmService) { }

  ngOnInit(): void {
    this._vendite = [];
    this._isSearchFormVisible = true;
    this._searchForm = this.builer.group({
      email: ['', Validators.required],
    });
  }

  get vendite() {
    return this._vendite;
  }
  get searchForm() {
    return this._searchForm;
  }

  get isSearchFormVisible() {
    return this._isSearchFormVisible;
  }


  set isSearchFormVisible(b: boolean) {
    this._isSearchFormVisible = b;
  }


  onSearch(): void {
    if (this.searchForm.get('email')?.value === '') {
      alert('Email vuota')
      return
    }
    this.refreshVendite();
  }

  onSaldaVendita(vendita: Vendita) {
    if (this.askService.askConfirm("saldare", "saldata", "la", "Vendita", "selezionata"))
      this.cassiereService.saldaVendita(vendita).subscribe(() => {
        this.refreshVendite();
      });
  }

  private refreshVendite() {
    this.cassiereService.getVenditeDaPagare(this.searchForm.get('email')?.value)
      .subscribe(v => {
        if (v.length == 0) {
          alert('Email associata a nessuna vendita!');
          return
        }
        this._vendite = v;
        this.isSearchFormVisible = false
      })
  }

  onLogout() {
    this.authenticationService.logout().subscribe(() => {
      this.router.navigate(['/login'])
      sessionStorage.clear()
    })
  }

}

