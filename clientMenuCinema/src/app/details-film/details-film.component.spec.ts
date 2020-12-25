import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsFilmComponent } from './details-film.component';

describe('DetailsFilmComponent', () => {
  let component: DetailsFilmComponent;
  let fixture: ComponentFixture<DetailsFilmComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailsFilmComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsFilmComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
