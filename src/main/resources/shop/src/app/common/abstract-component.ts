import {AfterViewInit, ChangeDetectorRef, Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {AbstractLanguageModel} from './abstract-language.model';

@Component({
  template: ''
})
export abstract class AbstractComponent implements OnInit, OnDestroy, AfterViewInit, OnChanges {

  protected constructor(protected cdr: ChangeDetectorRef,
                        protected abstractLanguageModel: AbstractLanguageModel) {
  }

  public getCurrentLanguagePackage(): any {
    return this.abstractLanguageModel.currentLanguagePackage();
  }

  public ngOnInit(): void {
    this.abstractLanguageModel.onLanguageChange.add(this.onLanguageChangeHandler, this);
  }

  public ngOnChanges(changes: SimpleChanges): void {
  }

  public ngAfterViewInit(): void {
  }

  public ngOnDestroy(): void {
    this.abstractLanguageModel.onLanguageChange.remove(this.onLanguageChangeHandler, this);
  }

  protected onLanguageChangeHandler(): void {
    this.cdr.detectChanges();
  }

}


