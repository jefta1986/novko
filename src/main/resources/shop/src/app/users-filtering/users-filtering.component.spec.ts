import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsersFilteringComponent } from './users-filtering.component';

describe('ProductFilteringComponent', () => {
  let component: UsersFilteringComponent;
  let fixture: ComponentFixture<UsersFilteringComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsersFilteringComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsersFilteringComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
