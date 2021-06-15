import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminProductCodeComponent } from './admin-product-code.component';

describe('AllProductsComponent', () => {
  let component: AdminProductCodeComponent;
  let fixture: ComponentFixture<AdminProductCodeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminProductCodeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProductCodeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
