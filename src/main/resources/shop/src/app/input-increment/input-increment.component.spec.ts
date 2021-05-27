import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputIncrementComponent } from './input-increment.component';

describe('InputIncrementComponent', () => {
  let component: InputIncrementComponent;
  let fixture: ComponentFixture<InputIncrementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputIncrementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputIncrementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
