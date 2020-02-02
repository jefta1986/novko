import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedProductComponent } from './selected-product.component';

describe('SelectedProductComponent', () => {
  let component: SelectedProductComponent;
  let fixture: ComponentFixture<SelectedProductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelectedProductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelectedProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
