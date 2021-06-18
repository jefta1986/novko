import {ChangeDetectorRef, Component, Input, OnDestroy, OnInit} from '@angular/core';
import {CommonAbstractComponent} from '../common/common-abstract-component';
import {CommonLanguageModel} from '../common/common-language.model';
import {NoItem} from '../common/common-language.interface';

@Component({
  selector: 'app-no-entries-list',
  templateUrl: './no-entries-list.component.html',
  styleUrls: ['./no-entries-list.component.scss']
})
export class NoEntriesListComponent extends CommonAbstractComponent implements OnInit, OnDestroy {

  @Input() translationType: NoItem | undefined;

  constructor(protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
  }

}
