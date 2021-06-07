import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminAddCategory } from './admin-add-category.component';

describe('AddcategorydialogComponent', () => {
  let component: AdminAddCategory;
  let fixture: ComponentFixture<AdminAddCategory>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdminAddCategory ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminAddCategory);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
