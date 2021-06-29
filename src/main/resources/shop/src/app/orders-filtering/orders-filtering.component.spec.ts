import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdersFilteringComponent } from './orders-filtering.component';

describe('ProductFilteringComponent', () => {
  let component: OrdersFilteringComponent;
  let fixture: ComponentFixture<OrdersFilteringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrdersFilteringComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OrdersFilteringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
