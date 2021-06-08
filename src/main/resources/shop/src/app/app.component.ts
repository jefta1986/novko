import {
  AfterContentInit,
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  ElementRef,
  HostListener,
  NgZone,
  OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import {fromEvent, Observable, Subject, Subscription} from 'rxjs';
import {Router} from '@angular/router';
import {Category} from './data/category';
import {CategoriesModel} from './data/models/categories.model';
import {CommonAbstractComponent} from './common/common-abstract-component';
import {CommonLanguageModel} from './common/common-language.model';
import {debounceTime} from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent extends CommonAbstractComponent implements AfterContentInit, OnInit, OnDestroy {

  @ViewChild('appNavigation') appNavigation: ElementRef | undefined;

  public get categories(): Category[] {
    return this._categoriesModel.categories;
  }

  public get showNavigation(): boolean {
    return !this._router.url.includes('/login');
  }

  public navBarHeight: string = 'auto';
  private resize: Subscription | undefined;
  private resizeObservable: Observable<Event> | undefined;

  constructor(private _router: Router,
              private _categoriesModel: CategoriesModel,
              protected cdr: ChangeDetectorRef,
              protected commonLanguageModel: CommonLanguageModel) {
    super(cdr, commonLanguageModel);
  }

  ngOnInit(): void {
    super.ngOnInit();
    this.resizeObservable = fromEvent(window, 'resize');
    this.resize = this.resizeObservable.pipe(debounceTime(100)).subscribe(
      () => this.resizeHandler()
    );
  }

  ngOnDestroy(): void {
    super.ngOnDestroy();
    if (this.resize) {
      this.resize.unsubscribe();
    }
  }

  ngAfterContentInit(): void {
    this.resizeHandler();
  }

  resizeHandler(): void {
    if (this.appNavigation) {
      this.navBarHeight = `${this.appNavigation.nativeElement.offsetHeight}px`;
    }
  }

}
