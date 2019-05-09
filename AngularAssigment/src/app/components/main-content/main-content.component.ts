import { Component, OnInit } from '@angular/core';
import { DataService } from '../../services/data-service/data.service';
import { Company } from 'src/app/models/company.model';
import { Companies } from 'src/app/models/companies';

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent implements OnInit {

  companies: Companies;
  searchName: any = {name: ''};

  constructor(private data: DataService) { 

  }

  ngOnInit() {
    this.data.getCompanies().subscribe(
      data => {
        this.companies = data as Companies;
      }
    )
  }

  
  /**
   * Checks length of input value and 
   * @param {event} e
   */
  checkLength(e){
    if (e.length > 2)
      this.searchName.name = String(e);
    else 
      this.searchName.name = '';
  }
  
}
