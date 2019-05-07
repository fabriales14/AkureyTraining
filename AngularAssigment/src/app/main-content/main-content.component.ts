import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Observable } from 'rxjs';
import { FilterPipe } from 'ngx-filter-pipe';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  companies$: Object;
  searchName: any = {name: ''};

  constructor(private data: DataService, private filter: FilterPipe) { 

  }

  ngOnInit() {
    this.data.getCompanies().subscribe(
      data => this.companies$ = data
    )
  }

  checkLength(e){
    if (e.length > 2)
      this.searchName.name = String(e)
    else 
      this.searchName.name = ''
  }

  

}
