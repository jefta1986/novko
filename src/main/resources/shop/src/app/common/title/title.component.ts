import {Component, Input, OnInit} from '@angular/core';
import {AdditionalLinks} from '../../data/additional-links';

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
  styleUrls: ['./title.component.css']
})
export class TitleComponent implements OnInit {

  @Input() additionalLinks: AdditionalLinks[] = [];
  @Input() title = '';
  @Input() subtitle = '';
  @Input() noMargin = false;

  constructor() {
  }

  ngOnInit(): void {
  }

}
