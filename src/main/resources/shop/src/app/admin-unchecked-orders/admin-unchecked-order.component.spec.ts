import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminUncheckedOrderComponent } from './admin-unchecked-order.component';

describe('AdminComponent', () => {
  let component: AdminUncheckedOrderComponent;
  let fixture: ComponentFixture<AdminUncheckedOrderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminUncheckedOrderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminUncheckedOrderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
